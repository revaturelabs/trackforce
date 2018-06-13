import {Associate} from "./associate.model";
import {Trainer} from "./trainer.model";
import { BatchLocation } from "./batch-location.model";

/** @author Princewill Ibe
 *
 * Holds data needed by front end for a batch
 */
export class Batch {
  id: number;
  batchName: string;
  curriculumName: string;
  location: BatchLocation;
  startDate: Date;
  endDate: Date;
  associates: Associate[]
  trainer: Trainer;
  coTrainer: Trainer[];
}
