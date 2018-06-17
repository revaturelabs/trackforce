/**
 * Used in the data-sync.service
 * Note made 6/7/2018
 * Reviewed by Max
 */
import { Associate } from "./associate.model";

export class MarketingStatus {
  id: number;
  name: string;
  associates: Associate[];
}
