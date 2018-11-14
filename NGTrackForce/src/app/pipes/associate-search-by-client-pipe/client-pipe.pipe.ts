// import { Pipe, PipeTransform } from '@angular/core';

// @Pipe({
//     name: 'pipeByClient'
// })

// /**
//  * Pipe to filter searches based on select option of Client
//  * @author Cyril Mathew
//  */
// export class AssociateSearchByClientPipe implements PipeTransform {
//     transform(items: any[], searchText: string): any[] {

//         if (!items) {
//             return [];
//         }

//         if (!searchText) {
//             return items;
//         }

//         //return results that contain firstname, lastname, status, client, id that matches a client selection
//         return items.filter(associate => {
//             return ((associate.client != null)? associate.client.name === searchText: false);
//         });
//     }
// }
