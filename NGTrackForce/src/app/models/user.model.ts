/**
 * Model containing information about the current user
 * @author Leng
 */

 export class User{
     username: string;
     passwordHash: string;
     tfRoleId: number;
     verified: boolean;
     userId: number;
     token: string;
 }
