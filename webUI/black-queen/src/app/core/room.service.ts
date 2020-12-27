import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../app-constants";
import { UserService } from "./user.service";

@Injectable({
    providedIn: 'root'
})
export class RoomService {

    constructor(private userService: UserService, private httpClient: HttpClient) {}

    createRoom() {
        // get user from userService
        // create room API call
        return this.httpClient.post(AppConstants.SERVICE_URL.concat(AppConstants.URL.CREATE_ROOM_API), {
            id: this.userService.loggedInUser?.id
        });
    }

    /**
     * This method calls JOIN_ROOM_API
     * @param roomId Room ID
     */
    joinRoom(roomId: string) {
        // get user from userService
        // join room with room ID
        return this.httpClient.post(AppConstants.SERVICE_URL.concat(AppConstants.URL.JOIN_ROOM_API), {
            roomId,
            userId: this.userService.loggedInUser?.id
        });
    }
}