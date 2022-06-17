import random
import socket


class Sender:
    def __init__(self, ip, port, multipleOf):
        self.UDP_IP = ip
        self.UDP_PORT = port
        self.multipleValue = multipleOf

        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.sock.bind((self.UDP_IP, self.UDP_PORT))

    def send(self, ip, port):
        value = str(self.multipleValue)
        self.sock.sendto(value.encode(), (ip, port))


class Receiver:
    def __init__(self, ip, port, multipleOf):
        self.UDP_IP = ip
        self.UDP_PORT = port
        self.multipleValue = multipleOf

        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.sock.bind((self.UDP_IP, self.UDP_PORT))

    def receive(self):
        data, addr = self.sock.recvfrom(1024)
        if int(data) % int(self.multipleValue) == 0:
            print("received message: %s" % data)
            self.sock.sendto(b"ACK", addr)


if __name__ == "__main__":
    N1 = Sender("127.0.0.1", 6001, 5)
    N2 = Receiver("127.0.0.2", 6002, 5)
    N3 = Receiver("127.0.0.3", 6003, 3)

    while N1.multipleValue < 3000:
        N1.multipleValue = N1.multipleValue + 1
        n = random.randint(1, 100)
        if n % 2 == 0:
            # send to node 2
            N1.send("127.0.0.2", 6002)
            N2.receive()
        else:
            # send to node 3
            N1.send("127.0.0.3", 6003)
            N3.receive()

