package workshop.structure101.persistence.memory;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import workshop.structure101.core.AccountType;
import workshop.structure101.core.CustomerRating;
import workshop.structure101.core.Score;
import workshop.structure101.persistence.CustomerRatingRepository;
import workshop.structure101.persistence.PersistenceException;

public class InMemoryCustomerRatingRepository implements CustomerRatingRepository {

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryCustomerRatingRepository.class);
    private final Set<String> deleted;
    private final ConcurrentMap<String, CustomerRating> updated;

    InMemoryCustomerRatingRepository() {
        deleted = new ConcurrentHashMap<String, String>().keySet("DEFAULT");
        updated = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<CustomerRating> selectById(String customerId) {
        if (deleted.contains(customerId)) {
            LOG.info("No rating available for customerId {} because it was already deleted.", customerId);
            return Optional.empty();
        }
        CustomerRating rating = updated.getOrDefault(customerId, generateCustomerRating(customerId));
        LOG.info("Retrieved rating {}", rating);
        return Optional.of(rating);
    }

    private CustomerRating generateCustomerRating(String customerId) {
        DataGenerator dataGenerator = new DataGenerator(customerId);
        AccountType accountType = getAccountType(dataGenerator);
        Score score = getScore(dataGenerator);
        String firstName = getFirstName(accountType, dataGenerator);
        String lastName = getLastName(accountType, dataGenerator);
        return new CustomerRating(customerId, firstName, lastName, accountType, score);
    }

    private String getFirstName(AccountType accountType, DataGenerator dataGenerator) {
        return accountType == AccountType.PRIVATE ?
            dataGenerator.fromList(RawData.FIRST_NAMES) :
            dataGenerator.fromList(RawData.COMPANY_NAMES);
    }

    private String getLastName(AccountType accountType, DataGenerator dataGenerator) {
        return accountType == AccountType.PRIVATE ?
            dataGenerator.fromList(RawData.LAST_NAMES) :
            dataGenerator.fromList(RawData.COMPANY_EXT);
    }

    private Score getScore(DataGenerator dataGenerator) {
        try {
            return dataGenerator.fromEnum(Score.class);
        } catch (ReflectiveOperationException e) {
            throw new PersistenceException("Failed to retrieve score");
        }

    }

    private AccountType getAccountType(DataGenerator dataGenerator) {
        try {
            return dataGenerator.fromEnum(AccountType.class);
        } catch (ReflectiveOperationException e) {
            throw new PersistenceException("Failed to retrieve account type");
        }
    }

    @Override
    public Optional<CustomerRating> deleteById(String customerId) {
        deleted.add(customerId);
        CustomerRating removed = updated.remove(customerId);
        if (removed == null) {
            removed = generateCustomerRating(customerId);
        }
        LOG.info("Deleted rating {}", removed);
        return Optional.of(removed);
    }

    @Override
    public Optional<CustomerRating> update(CustomerRating modifiedRating) {
        String customerId = modifiedRating.getCustomerId();
        if (deleted.contains(customerId)) {
            LOG.info("Could not update rating with customerId {}. It's already deleted.", customerId);
            return Optional.empty();
        }
        LOG.info("Updated rating {}", modifiedRating);
        return store(modifiedRating);
    }

    @Override
    public Optional<CustomerRating> insert(CustomerRating newRating) {
        String customerId = newRating.getCustomerId();
        if (updated.containsKey(customerId)) {
            LOG.info("Could not insert new rating with customerId {}. It already exists.", customerId);
            return Optional.empty();
        }
        LOG.info("Created rating {}", newRating);
        return store(newRating);
    }

    private Optional<CustomerRating> store(CustomerRating rating) {
        updated.put(rating.getCustomerId(), rating);
        return Optional.of(rating);
    }
}
