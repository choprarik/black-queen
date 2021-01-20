class BadRequestError extends Error {
    constructor(message) {
        super(message);
        this.name = "BadRequestError";
    }
}

class UnauthorizedRequestError extends Error {
    constructor(message) {
        super(message);
        this.name = "UnauthorizedRequestError";
    }
}

class ResourceNotFoundError extends Error {
    constructor(message) {
        super(message);
        this.name = "ResourceNotFoundError";
    }
}

module.exports = {
    BadRequestError: BadRequestError,
    UnauthorizedRequestError: UnauthorizedRequestError,
    ResourceNotFoundError: ResourceNotFoundError
}