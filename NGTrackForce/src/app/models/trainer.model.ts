import { User } from "./user.model";
import { Batch } from "./batch.model";

/**
 * Model information for trainer user.
 * @author Kirk
 * @since 6.18.6.12
 */

export class Trainer{

  id: number;
  user : User;
  firstName: string;
  lastName: string;
  primary : Batch[];
  coTrainer : Batch[];
  isApproved: 0;

}

