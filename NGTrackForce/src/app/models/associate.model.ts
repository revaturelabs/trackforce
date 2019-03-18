/**
 * Object to hold data regarding an associate.
 * @author Alex, Xavier
 */
import { Batch } from "./batch.model";
import { User } from "./user.model";
import { Client } from "./client.model";
import { MarketingStatus } from "./marketing-status.model";
import { EndClient } from "./end-client.model";
import { Interview } from "./interview.model";
import { Placement } from "./placement.model";

export class Associate {
  salesforceId: string;
  id: number;
  firstName: string;
  lastName: string;
  batch: Batch;
  user: User;
  marketingStatus: MarketingStatus;
  client: Client;
  endClient: EndClient;
  interview: Interview[];
  placement: Placement;
  clientStartDate: Date;
  stagingFeedback: string;

  constructor(
    newFirstName: string,
    newLastName: string,
    newUser: User,
    salesforceId?: string,
    newId?: number,
    newBatch?: Batch,
    newMarketingStatus?: MarketingStatus,
    newClient?: Client,
    newEndClient?: EndClient,
    newInterview?: Interview[],
    newPlacement?: Placement,
    newClientStartDate?: Date
  ) {
    this.firstName = newFirstName;
    this.lastName = newLastName;
    this.user = newUser;
    this.salesforceId = salesforceId;
    this.id = newId;
    this.batch = newBatch;
    this.marketingStatus = newMarketingStatus;
    this.client = newClient;
    this.endClient = newEndClient;
    this.interview = newInterview;
    this.placement = newPlacement;
    this.clientStartDate = newClientStartDate;
  }
}
