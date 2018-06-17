/**
 * Model containing information about the current user
 * @author Leng
 *
 * This will need to change to reflect changes being made to the DB
 * (If changes are made to the DB)
 * Note made 6/7/2018
 */
export class User {
  id: number;
  // name: string;
  username: string;
  hashedPassword: string;
  role: number;
  // Kirk: verified is not in UserJSON.java
  // Cameron : the commented out fields aren't
  // in the entity
  // verified: string;
  token: string;
  isApproved: number;
}

