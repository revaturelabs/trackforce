import { Associate } from "./associate.model";
import { Trainer } from "./trainer.model";
import { BatchLocation } from "./batch-location.model";
import { Curriculum } from "./curriculum.model";

/** 
 * 
 * @author Princewill Ibe
 *
 * Holds data needed by front end for a batch
 */
export class Batch {
  id: number;
  batchName: string;
  curriculumName: Curriculum;
  location: BatchLocation;
  startDate: number;
  endDate: number;
  associates: Associate[]
  trainer: Trainer;
  coTrainer: Trainer[];
}
