import {Associate} from './associate.model';
import {Placement} from './placement.model';
import {Interview} from './interview.model';
import {StatusInfo} from './status-info.model';

/**
 * Model holding data relating to a client
 * @author Alex, Xavier
 */
export class Client {
  id: number;
  // What is the difference between the name and the tfClientName
  // Kirk: name is not used in NGTrackForce. name can be deleted.
  name: string;
  tfClientName: string;
  placements: Placement[];
  associates: Associate[];
  interviews: Interview[];
  stats: StatusInfo;

  constructor() {
  }
}
