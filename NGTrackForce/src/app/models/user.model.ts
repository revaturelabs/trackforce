/**
 * Model containing information about the current user
 * @author Leng
 */

 export class User{
     username: string;
     passwordHash: string;
     verified: boolean;
     tfRoleId: number;
     userId: number;
     token: string;
 }
