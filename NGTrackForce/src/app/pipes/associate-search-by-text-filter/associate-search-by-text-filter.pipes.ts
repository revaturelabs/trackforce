import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filterByText'
})

/**
 * Pipe to filter searches based on user input
 * @author Alex, Xavier
 */
export class AssociateSearchByTextFilter implements PipeTransform {
    transform(items: any[], searchText: string): any[] {

        if (!items) {
            return [];
        } 
        
        if (!searchText) {
            return items;
        } 
        
        searchText = searchText.toLowerCase();

        //return results that contain firstname, lastname, status, client, id
        return items.filter(associate => {
            return ( (associate.firstName != null? associate.firstName.toLowerCase().includes(searchText): false) 
                ||  (associate.lastName != null? associate.lastName.toLowerCase().includes(searchText): false ));
        });
    }
}