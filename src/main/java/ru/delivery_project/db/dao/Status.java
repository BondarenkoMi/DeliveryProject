package ru.delivery_project.db.dao;

public enum Status {
    REGISTERED, IN_DELIVERY, DELIVERED, CANCELLED;
    public static Status getStatus(String status) {
        Status result = null;
        switch (status) {
            case "Оформлен":
                result =  Status.REGISTERED;
                break;
            case "Доставляется":
                result =  Status.IN_DELIVERY;
                break;
            case "Доставлен":
                result =  Status.DELIVERED;
                break;
            case "Отменен":
                result =  Status.CANCELLED;
                break;
        }
        return result;
    }
}
