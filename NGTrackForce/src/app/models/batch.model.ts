import { Associate } from "./associate.model";
import { Trainer } from "./trainer.model";
import { BatchLocation } from "./batch-location.model";
import { Curriculum } from "./curriculum.model";

/** @author Princewill Ibe
 *
 * Holds data needed by front end for a batch
 */
export class Batch {
  id: number;
  batchName: string;
  curriculumName: Curriculum;
  location: BatchLocation;
<<<<<<< HEAD
  startDate: Date;
  endDate: Date;
  associates: Associate[];
=======
  startDate: number;
  endDate: number;
  associates: Associate[]
>>>>>>> client1804-2
  trainer: Trainer;
  coTrainer: Trainer[];
}
