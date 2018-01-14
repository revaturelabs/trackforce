export class SelectedStatusConstants {
    public static readonly TRAINING = "Training";
    public static readonly OPEN = "Open";
    public static readonly SELECTED = "Selected";
    public static readonly CONFIRMED = "Confirmed";
    public static readonly RESERVED = "Reserved";
    public static readonly MAPPED = "Mapped";
    public static readonly UNMAPPED = "Unmapped";
    public static readonly RESERVED_OPEN = "Reserved/Open";

    public static readonly TRAINING_JSON = { id: 0, name: SelectedStatusConstants.TRAINING };
    public static readonly OPEN_JSON = { id: 1, name: SelectedStatusConstants.OPEN };
    public static readonly SELECTED_JSON = { id: 2, name: SelectedStatusConstants.SELECTED };
    public static readonly CONFIRMED_JSON = { id: 3, name: SelectedStatusConstants.CONFIRMED };
    public static readonly RESERVED_JSON = { id: 1, name: SelectedStatusConstants.RESERVED };
    public static readonly MAPPED_JSON = { id: 0, name: SelectedStatusConstants.MAPPED };
    public static readonly UNMAPPED_JSON = { id: 1, name: SelectedStatusConstants.UNMAPPED };
    public static readonly RESERVED_OPEN_JSON = { id: 1, name: SelectedStatusConstants.RESERVED_OPEN };

    public static readonly MAPPED_LABELS: string[] = [SelectedStatusConstants.TRAINING, SelectedStatusConstants.RESERVED, SelectedStatusConstants.SELECTED, SelectedStatusConstants.CONFIRMED]
    public static readonly UNMAPPED_LABELS: string[] = [SelectedStatusConstants.TRAINING, SelectedStatusConstants.OPEN, SelectedStatusConstants.SELECTED, SelectedStatusConstants.CONFIRMED]
    public static readonly DEPLOYED_LABELS: string[] = [SelectedStatusConstants.MAPPED, SelectedStatusConstants.UNMAPPED];
    public static readonly UNDEPLOYED_LABELS: string[] = [SelectedStatusConstants.MAPPED, SelectedStatusConstants.UNMAPPED];
    public static readonly CLIENT_LABELS: string[] = [SelectedStatusConstants.TRAINING, SelectedStatusConstants.RESERVED_OPEN, SelectedStatusConstants.SELECTED, SelectedStatusConstants.CONFIRMED];
}