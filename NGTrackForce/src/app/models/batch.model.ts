import {Associate} from "./associate.model";

/** @author Princewill Ibe
 *
 * Holds data needed by front end for a batch
 */
export class Batch {
  id: number;
  batchName: string;
  curriculumName: string;
  location: string;
  startDate: Date;
  endDate: Date;
  associates: Associate[]
}
