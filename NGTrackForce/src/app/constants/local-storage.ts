export class LocalStorageUtils {
  public static readonly DEPLOYED_DATA_KEY = 'deployedData'
  public static readonly UNDEPLOYED_DATA_KEY = 'undeployedData'
  // Roles
  public static readonly CURRENT_USER_KEY = 'currentUser'
  public static readonly CURRENT_ASSOCIATE_KEY = 'currentAssociate'
  public static readonly CURRENT_ASSOCIATES_PLURAL_KEY = 'currentAssociates'
  public static readonly CURRENT_TRAINER_KEY = 'currentTrainer'

  public static readonly MAPPED_DATA_KEY = 'mappedData'
  public static readonly UNMAPPED_DATA_KEY = 'unmappedData'

  // Local cache data
  public static readonly CACHE_ENABLED = true
  public static readonly CACHE_ASSOCIATE_ALL = 'associateAll'
  public static readonly CACHE_ASSOCIATE_COUNT = 'associateCount'
  public static readonly CACHE_ASSOCIATE_BY_USER_ID = 'associateByUserId'
  public static readonly CACHE_ASSOCIATE_PAGE = 'associatePage'
  public static readonly CACHE_BATCHES_WITHIN_DATES = 'batchesWithinDates'
  public static readonly CACHE_CLIENT_ALL = 'clientGetAll'
  public static readonly CACHE_CLIENT_COUNT = 'clientCount'
  public static readonly CACHE_CLIENT_ASSOCIATE_COUNT = 'clientAssociateCount'
  public static readonly CACHE_CURRICULUM_ALL = 'clientCurriculumAll'

  // Sample test data
  public static readonly TEST_DEPLOYED_DATA_VALUE = '[102,68]'
  public static readonly TEST_UNDEPLOYED_DATA_VALUE = '[88,391]'
  public static readonly TEST_UNMAPPED_DATA_VALUE = '[1,2,3,4]'
  public static readonly TEST_CURRENT_USER_VALUE = '{"id":0,"username":"mockUsername","isApproved":1,"token":"","role":5}'
  
  public static readonly TEST_USER_NAME = 'mockUsername'
  public static readonly TEST_USER_PASSWORD = 'mockPassword'
  public static readonly TEST_USER_FIRST_NAME = 'mockFirstName'
  public static readonly TEST_USER_LAST_NAME = 'mockLastName'
}
