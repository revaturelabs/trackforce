import {Associate} from "./associate.model";
import {Placement} from "./placement.model";
import { Interview } from "./interview.model";


export class EndClient {
  id: number;
  // Kirk:  I assume name is referring to tfEndClientname within EndClientInfo.java.
  name: string;
  associates: Associate[];
  placements: Placement[];
  interviews: Interview[];
}
