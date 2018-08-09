import { User } from './user.model';

export class UserAndCreatorRoleContainer{
    user: User;
    creatorRole: number;

    constructor(
        newUser?: User,
        newCreatorRole?: number
    ){
        this.user = newUser;
        this.creatorRole = newCreatorRole;
    }
}