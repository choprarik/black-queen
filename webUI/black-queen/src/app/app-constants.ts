export class AppConstants {
    public static readonly SERVICE_URL = 'http://localhost:8765/'
    public static readonly URL = class {
        public static readonly LOGIN_API = 'login';
        public static readonly SIGNUP_API = 'signup';
        public static readonly USERS_API = 'users';
        public static readonly USER_API = 'user';
        public static readonly CREATE_ROOM_API = 'room';
        public static readonly JOIN_ROOM_API = 'room/join';
    };
}