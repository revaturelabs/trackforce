export enum SortOption {
  ID = 'id',
  FNAME = 'firstName',
  LNAME = 'lastName',
  BATCH = 'batch.batchName',
  MARKET = 'marketingStatus.name',
  CLIENT = 'client.name'
}

export enum SortedBy {
  sortByIdAsc = 'sortByIdAsc',
  sortByFNameAsc = 'sortByFNameAsc',
  sortByLNameAsc = 'sortByLNameAsc',
  sortByMarketAsc = 'sortByMarketAsc',
  sortByBatchAsc = 'sortByBatchAsc',
  sortByClientAsc = 'sortByClientAsc'
}
