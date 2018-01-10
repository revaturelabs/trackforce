// This automagical code obtained from https://netbasal.com/automagically-unsubscribe-in-angular-4487e9853a88
export function AutoUnsubscribe( constructor ) {

    const original = constructor.prototype.ngOnDestroy;
  
    constructor.prototype.ngOnDestroy = function () {
      for ( let prop in this ) {
        const property = this[ prop ];
        if ( property && (typeof property.unsubscribe === "function") ) {
          property.unsubscribe();
        }
      }
      original && typeof original === "function" && original.apply(this, arguments);
    };
    
}