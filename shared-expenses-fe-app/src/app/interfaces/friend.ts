import { UUID } from "crypto";

export interface Friend {
    id: UUID,
    name: string,
    email: string
}