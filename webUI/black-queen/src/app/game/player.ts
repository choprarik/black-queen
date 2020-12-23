import { Card } from "./card/card";

export class Player {
    name: string;
    isTurn: boolean = false;
    points: number = 0;
    isPartner: boolean = false;
    isDealer: boolean = false;
    isDefault: boolean = false;
    card: Card;
}