/**
 * Model containing information about the current user
 * @author Leng
 *
 * This will need to change to reflect changes being made to the DB
 * (If changes are made to the DB)
 * Note made 6/7/2018
 */

export class User {
  username: string;
  passwordHash: string;
  tfRoleId: number;
  userId: number;
  token: string;
  associateId: number;
  // Kirk: verified is not in UserJSON.java
  verified: string;
}
