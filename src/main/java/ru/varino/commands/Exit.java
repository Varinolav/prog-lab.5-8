package ru.varino.commands;

import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class Exit extends Command {
    public Exit() {
        super("exit", "��������� ��������� (��� ���������� � ����)");
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        return ResponseEntity.serverError().body("������ ��������� ����������");
    }

}
