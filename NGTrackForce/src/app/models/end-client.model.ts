import { Associate } from "./associate.model";
import { Placement } from "./placement.model";


export class EndClient{
    id: number;
    name: string;
    associates: Associate[];
    placements: Placement[];
}