import { Pipe, PipeTransform } from '@angular/core';
/**
 * @author Han Jung
 * @description Filters out array of strings based on search criteria
 */
@Pipe({
  name: 'searchFilter'
})
export class SearchFilterPipe implements PipeTransform {
  /**
    *Custom pipes need to implement the tranform function
    *
    *@param {any[]} items
    *array of strings to be filtered
    *
    *@param {string} searchText
    *the text to be searched against
    *
    *@return the filtered items
    */
  transform(items: any[], searchText: string): any[] {
    if(!items) return [];
    if(!searchText) return items;

    searchText=searchText.toLowerCase();
    return items.filter( it => {
      return it.toLowerCase().includes(searchText);
    });
  }

}
