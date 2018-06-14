/**
 * Model containing information about the current user
 * @author Leng
 *
 * This will need to change to reflect changes being made to the DB
 * (If changes are made to the DB)
 * Note made 6/7/2018
 */
import { Role } from "./role.model";
export class User {
  id : number;
  name : string;
  username: string;
  hashedPassword: string;
  role : Role;
  // Kirk: verified is not in UserJSON.java
  // Cameron : the commented out fields aren't
  // in the entity
  verified: string;
  // tfRoleId: number;
  // userId: number;
  // token: string;
  // associateId: number;
  isApproved: boolean;
}
