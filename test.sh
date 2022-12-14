#!/bin/bash
assert() {
    expected="$1"
    input="$2"

    ./run.sh "$input" > tmp.s || exit
    docker run -i -u root -v $(pwd):/root rvcc /bin/bash -c '$RISCV/bin/riscv64-unknown-linux-gnu-gcc -static -o /root/tmp /root/tmp.s'
    docker run -i -u root -v $(pwd):/root rvcc /bin/bash -c '$RISCV/bin/qemu-riscv64 -L $RISCV/sysroot /root/tmp'
    actual="$?"

    if [ "$actual" = "$expected" ]; then
        echo "$input => $actual"
    else 
        echo "$input => $expected execpted, but got $actual"
        exit 1
    fi
}

assert 0 0
assert 42 42
assert 3 '1+2'
assert 21 '5+20-4'
assert 41 ' 12 + 34 - 5 '

echo passed!