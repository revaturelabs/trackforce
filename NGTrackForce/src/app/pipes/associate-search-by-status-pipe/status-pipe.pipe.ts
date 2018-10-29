// import { Pipe, PipeTransform } from '@angular/core';

// @Pipe({
//     name: 'pipeByStatus'
// })

// /**
//  * Pipe to filter searches based on select option of Marketing Status
//  * @author Cyril Mathew
//  */
// export class AssociateSearchByStatusPipe implements PipeTransform {
//     transform(items: any[], searchText: string): any[] {

//         if (!items) {
//             return [];
//         }

//         if (!searchText) {
//             return items;
//         }

//         //return results that contain firstname, lastname, status, client, id that matches a marketing status selection
//         return items.filter(associate => {
//             return ((associate.marketingStatus != null)? associate.marketingStatus.name === searchText: false);
//         });
//     }
// }
