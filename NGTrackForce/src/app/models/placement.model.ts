import { Associate } from "./associate.model";
import { Client } from "./client.model";
import { EndClient } from "./end-client.model";

/**
 * Used in the client and the end-client models
 * Note made 6/7/2018
 * @author Amelia
 */
export class Placement {
  id: number;
  associate: Associate;
  client: Client;
  endClient: EndClient;
  start: Date;
  end: Date;
}
