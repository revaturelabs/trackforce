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

        let firstName:string;
        let lastName:string;
        let marketingStatus:string;
        let client:string;
        //return results that contain firstname, lastname, status, client, id
        return items.filter(associate => {
            associate.firstName != null? firstName = associate.firstName : false;
            associate.lastName != null? lastName = associate.lastName: false ;
            associate.marketingStatus != null? marketingStatus = associate.marketingStatus.name: false ;
            associate.client != null? client = associate.client.name: false ;
            return (firstName+ " " +lastName+ " " + marketingStatus + " " + client).toLowerCase().includes(searchText);
        });
    }
}