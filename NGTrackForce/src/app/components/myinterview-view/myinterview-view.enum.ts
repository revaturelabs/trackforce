/**
 * Enum for status messages after updating or submitting interviews
 * {} is a placeholder, similar to Python's, so the message can be generalized.
 * {} is for 'submission' or 'update'.
 */
export enum InterviewStatusMsg {
  SUCCESS = 'Interview {} is successful!',
  WAIT = 'Interview {} is pending, please wait',
  FAILURE = 'There was an error on the server',
  CONFLICT = 'There is a conflicting interview'
}

/**
 * Alert class names to style message
 */
export enum AlertClass {
  SUCCESS = 'alert-success',
  WAIT = 'alert-warning',
  FAILURE = 'alert-danger'
}

/**
 * Enum holding property keys for either the submit status message or the update status message
 * Allows us to manage status messages in a generalized and DRY manner
 */
export enum StatusProp {
  SUBMIT = 'submitInterviewStatus',
  UPDATE = 'updateInterviewStatus'
}
