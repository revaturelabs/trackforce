/**
 * Enumeration containing all expected display messages for the create user page
 */
export enum StatusMessage {
    INVALID_PASS = 'Password must have a number, a capital letter and a special character',
    INVALID_USER = 'Invalid username, please do not use spaces or special characters',
    MISMATCH = 'Passwords do not match!',
    NONUNIQUE = 'Username is not unique!',
    SUCCESS = 'User created successfully',
    ERROR = 'Error: new user not created!'
}
