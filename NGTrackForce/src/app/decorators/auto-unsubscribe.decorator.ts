/**
 * Decorator for automatically unsubscribing from any and all observables (so that you don't need to bloat 
 *  your codebase by having instances of said observables floating around solely for the purpose of manually
 *  unsubscribing!)
 * @param constructor 
 * 
 * - This automagical code obtained from https://netbasal.com/automagically-unsubscribe-in-angular-4487e9853a88
 * - There is also a github repository https://github.com/NetanelBasal/ngx-auto-unsubscribe
 * - It's possible to just npm install a dependency and get the same functionality
 * Note made on 6/6/2018
 */

// This automagical code obtained from https://netbasal.com/automagically-unsubscribe-in-angular-4487e9853a88
export function AutoUnsubscribe( constructor ) {

    // make copy of the ngOnDestroy callback
    const original = constructor.prototype.ngOnDestroy;
  
    // upon destruction of the component...
    constructor.prototype.ngOnDestroy = function () {
      // ...iterate thru its properties...
      for ( let prop in this ) {
        const property = this[ prop ];
        // if property exists and has-a unsubscribe...
        if ( property && (typeof property.unsubscribe === "function") ) {
          //...call it
          property.unsubscribe();
        }
      }
      // if the callback we copied was function, we invoke it now with any arguments that were passed in
      //  via constructor
      original && typeof original === "function" && original.apply(this, arguments);
    };
   
}