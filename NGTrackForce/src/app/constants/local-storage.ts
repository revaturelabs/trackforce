export class LocalStorage {
  public static readonly DEPLOYED_DATA_KEY = 'deployedData'
  public static readonly UNDEPLOYED_DATA_KEY = 'undeployedData'
  // Roles
  public static readonly CURRENT_USER_KEY = 'currentUser'
  public static readonly CURRENT_ASSOCIATE_KEY = 'currentAssociate'
  public static readonly CURRENT_ASSOCIATES_PLURAL_KEY = 'currentAssociates'
  public static readonly CURRENT_TRAINER_KEY = 'currentTrainer'

  public static readonly MAPPED_DATA_KEY = 'mappedData'
  public static readonly UNMAPPED_DATA_KEY = 'unmappedData'

  // Sample test data
  public static readonly TEST_DEPLOYED_DATA_VALUE = '[102,68]'
  public static readonly TEST_UNMAPPED_DATA_VALUE = '[1,2,3,4]'
  public static readonly TEST_USER_NAME = 'mockUsername'
  public static readonly TEST_USER_PASSWORD = 'mockPassword'
  public static readonly TEST_USER_FIRST_NAME = 'mockFirstName'
  public static readonly TEST_USER_LAST_NAME = 'mockLastName'
}
