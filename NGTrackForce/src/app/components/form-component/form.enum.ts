// Server status messages, {} is placeholder, handled by _formatStatusMsg method
export enum FormStatus {
  SUCCESS = 'Associate {} was successful',
  WAIT = 'Please wait, your {} is pending',
  FAILURE = 'There was an error on the server'
}

// Enumeration for status message properties on form.component class
export enum StatusProp {
  APPROVE = 'approveStatus',
  UPDATE = 'formStatus',
}

// Enumeration for properties that hold the bootstrap css classes
export enum StatusClass {
  UPDATE_CLASS = 'formStatusClass',
  APROVE_CLASS = 'approveStatusClass',
}

export enum MarketingStatus {

}
