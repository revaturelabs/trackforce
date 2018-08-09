import { Associate } from './associate.model';
import { Placement } from './placement.model';
import { Interview } from './interview.model';
import { StatusInfo } from './status-info.model';

/**
 * Model holding data relating to a client
 * @author Alex, Xavier
 */
export class Client {
  id: number;
  name: string;
  placement: Placement[];
  associate: Associate[];
  interview: Interview[];
  
  stats: StatusInfo;

  constructor(
    newId?: number,
    newName?: string,
    newPlacements?: Placement[],
    newAssociates?: Associate[],
    newInterviews?: Interview[],
    newStats?: StatusInfo
  ) {
    this.id = newId;
    this.name = newName;
    this.placement = newPlacements;
    this.associate = newAssociates;
    this.interview = newInterviews;
    this.stats = newStats;
  }
}
