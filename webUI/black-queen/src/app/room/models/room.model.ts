export enum ROOM_STATUS {
    OPEN,
    CLOSE,
    WAITING,
    OCCUPIED
}
export class RoomModel {
    id?: string;
    hostId?: string;
    members?: string[];
    status?: ROOM_STATUS;
    gameId?: string;
}