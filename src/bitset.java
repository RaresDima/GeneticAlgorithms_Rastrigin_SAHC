class bitset {

    public int[] bits = new int[100];
    public int length;

    bitset(int len)                             {

        length = len;

        for (int i : bits)
            i = 0;

    }
    bitset(int len, int sample_bit)             {

        length = len;

        for (int i : bits)
            i = sample_bit;
    }
    bitset(int[] sample_bitset)                 {

        length = sample_bitset.length;

        for (int i : bits)
            i = 0;

        System.arraycopy(sample_bitset, 0, bits, 0, sample_bitset.length);
    }
    bitset(bitset source_bitset)                {

        length = source_bitset.length;

        System.arraycopy(source_bitset.bits, 0, bits, 0, length);
    }

    public void flip(int index)                 {

        bits[index] = 1 - bits[index];
    }

    public void copy_from(bitset source_bitset) {

        length = source_bitset.length;

        System.arraycopy(source_bitset.bits, 0, bits, 0, length);
    }

    public int to_int()                         {

        int value = 0;

        for (int i = 0; i < length; ++i)
            value += bits[i] * Math.pow(2, length - (i + 1));

        return value;
    }
    public void from_int(int value)             {

        if(value > Math.pow(2, length) - 1 || value < 0){
            System.out.print("\n================\nILLEGAL ARGUMENT\n================\n");
            return;
        }

        for(int i : bits)
            i = 0;

        for(int i = 0; i < length; ++i) {
            bits[length - (i + 1)] = value % 2;
            value /= 2;
        }
    }
}
