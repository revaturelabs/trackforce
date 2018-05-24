/**
 * Model containing information about the current user
 * @author Leng
 */

 export class User{
     username: String;
     passwordHash: String;
     tfRoleId: number;
     verified: boolean;
     userId: number;
     token: string;
 }
