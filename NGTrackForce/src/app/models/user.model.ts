/**
 * Model containing information about the current user
 * @author Leng
 */

 export class User{
     username: string;
     passwordHash: string;
     verified: string;
     tfRoleId: number;
     userId: number;
     token: string;
     associateId: number;
 }
