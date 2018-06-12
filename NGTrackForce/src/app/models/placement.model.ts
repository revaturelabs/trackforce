import {Associate} from "./associate.model";

/**
 * Used in the client and the end-client models
 * Note made 6/7/2018
 * @author Amelia
 */
export class Placement {
  id: number;
  associate: Associate;
  start: Date;
  end: Date;
}
