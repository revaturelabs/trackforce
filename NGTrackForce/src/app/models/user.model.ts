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
  username: string;
  password: string;
  role: number;
  token: string;
  isApproved: number;

  constructor(
    newUsername: string,
    newPassword: string,
    newRole: number,
    newIsApproved: number,
    newId?: number,
    newToken?: string
  ) {
      this.username = newUsername;
      this.password = newPassword;
      this.role = newRole;
      this.isApproved = newIsApproved;
      this.id = newId;
      this.token = newToken;
    }
}
