import { Associate } from "./associate.model";
import { Placement } from "./placement.model";

/**
 * This is used in the interview model 
 * Note made 6/7/2018
 * @author Amelia
 */
export class EndClient{
    id: number;
    name: string;
    associates: Associate[];
    placements: Placement[];
}