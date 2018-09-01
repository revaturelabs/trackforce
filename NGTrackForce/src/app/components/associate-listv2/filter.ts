import { SortOrder } from './sort-order.enum';

/**
 * This class is used to specify the sorting order of objects
 */
export class Filter {
    // The key that the filter will sort on
    private key: string;

    // Specify if the list should be ascending or descending true = ascending
    public sortOrder: SortOrder;

    constructor(keyForFilter: string, initialSortOrder: SortOrder) {
        this.key = keyForFilter;
        this.sortOrder = initialSortOrder;
    }

    /**
     * Returns the current sorting order
     * 
     * @return {SortOrder} The order that the filter is set to sort in
     */
    getSortOrder(): SortOrder {
        return this.sortOrder;
    }

    /**
     * Sets the sorting order for the Filter
     * 
     * @param newOrder The order that the Filter should sort in
     */
    setSortOrder(newOrder: SortOrder): void {
        this.sortOrder = newOrder;
    }

    /**
     * Returns the key that the Filter applies to 
     * 
     * @return {string} Returns the current key the filter applies to
     */
    getKey(): string {
        return this.key;
    }

    /**
     * This will set the key to sort the object by
     * 
     * @param newKey The new key to apply the filter to
     */
    setKey(newKey: string): void {
        this.key = newKey;
    }
}