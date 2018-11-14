/**
 * Enumeration containing all expected display messages for the create user page
 *
          <h3>Password must contain the following:</h3>
          <p id="length" class="invalid">Minimum <b>8 characters</b></p>
          <p id="letter" class="invalid">At least one <b>lowercase</b> letter</p>
          <p id="capital" class="invalid">At least one <b>capital (uppercase)</b> letter</p>
          <p id="number" class="invalid">At least one <b>number</b></p>
          <p id="char" class="invalid">At least one special character <b>(.!@#$%^&*)</b></p>
          Password must have a number, a capital letter and a special character
 */
export enum StatusMessage {
    INVALID_PASS = 'Password must be at least 8 characters and include a number, a capital letter and a special character ',
    INVALID_USER = 'Alphabetical and numerical characters only, no spaces',
    MISMATCH = 'Passwords do not match!',
    NONUNIQUE = 'Username is not unique!',
    SUCCESS = 'User created successfully',
    ERROR = 'Error: new user not created!',
}
