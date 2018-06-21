package net.minecraft.nbt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.annotation.Nullable;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;

public class CompressedStreamTools {

    public static NBTTagCompound func_74796_a(InputStream inputstream) throws IOException {
        DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputstream)));

        NBTTagCompound nbttagcompound;

        try {
            nbttagcompound = func_152456_a((DataInput) datainputstream, NBTSizeTracker.field_152451_a);
        } finally {
            datainputstream.close();
        }

        return nbttagcompound;
    }

    public static void func_74799_a(NBTTagCompound nbttagcompound, OutputStream outputstream) throws IOException {
        DataOutputStream dataoutputstream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(outputstream)));

        try {
            func_74800_a(nbttagcompound, (DataOutput) dataoutputstream);
        } finally {
            dataoutputstream.close();
        }

    }
    
    public static void func_74793_a(NBTTagCompound p_74793_0_, File p_74793_1_) throws IOException
    {
        File file1 = new File(p_74793_1_.getAbsolutePath() + "_tmp");

        if (file1.exists())
        {
            file1.delete();
        }

        func_74795_b(p_74793_0_, file1);

        if (p_74793_1_.exists())
        {
            p_74793_1_.delete();
        }

        if (p_74793_1_.exists())
        {
            throw new IOException("Failed to delete " + p_74793_1_);
        }
        else
        {
            file1.renameTo(p_74793_1_);
        }
    }
    
    public static void func_74795_b(NBTTagCompound p_74795_0_, File p_74795_1_) throws IOException
    {
        DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(p_74795_1_));

        try
        {
            func_74800_a(p_74795_0_, dataoutputstream);
        }
        finally
        {
            dataoutputstream.close();
        }
    }
    
    @Nullable
    public static NBTTagCompound func_74797_a(File p_74797_0_) throws IOException
    {
        if (!p_74797_0_.exists())
        {
            return null;
        }
        else
        {
            DataInputStream datainputstream = new DataInputStream(new FileInputStream(p_74797_0_));
            NBTTagCompound nbttagcompound;

            try
            {
                nbttagcompound = func_152456_a(datainputstream, NBTSizeTracker.field_152451_a);
            }
            finally
            {
                datainputstream.close();
            }

            return nbttagcompound;
        }
    }

    public static NBTTagCompound func_74794_a(DataInputStream datainputstream) throws IOException {
        return func_152456_a((DataInput) datainputstream, NBTSizeTracker.field_152451_a);
    }

    public static NBTTagCompound func_152456_a(DataInput datainput, NBTSizeTracker nbtreadlimiter) throws IOException {
        // Spigot start
        if ( datainput instanceof io.netty.buffer.ByteBufInputStream )
        {
            datainput = new DataInputStream(new org.spigotmc.LimitStream((InputStream) datainput, nbtreadlimiter));
        }
        // Spigot end
        NBTBase nbtbase = func_152455_a(datainput, 0, nbtreadlimiter);

        if (nbtbase instanceof NBTTagCompound) {
            return (NBTTagCompound) nbtbase;
        } else {
            throw new IOException("Root tag must be a named compound tag");
        }
    }

    public static void func_74800_a(NBTTagCompound nbttagcompound, DataOutput dataoutput) throws IOException {
        func_150663_a((NBTBase) nbttagcompound, dataoutput);
    }

    private static void func_150663_a(NBTBase nbtbase, DataOutput dataoutput) throws IOException {
        dataoutput.writeByte(nbtbase.func_74732_a());
        if (nbtbase.func_74732_a() != 0) {
            dataoutput.writeUTF("");
            nbtbase.func_74734_a(dataoutput);
        }
    }

    private static NBTBase func_152455_a(DataInput datainput, int i, NBTSizeTracker nbtreadlimiter) throws IOException {
        byte b0 = datainput.readByte();
        nbtreadlimiter.func_152450_a(8); // Forge: Count everything!

        if (b0 == 0) {
            return new NBTTagEnd();
        } else {
            datainput.readUTF();
            NBTSizeTracker.readUTF(nbtreadlimiter, datainput.readUTF()); //Forge: Count this string.
            nbtreadlimiter.func_152450_a(32); //Forge: 4 extra bytes for the object allocation.
            NBTBase nbtbase = NBTBase.func_150284_a(b0);

            try {
                nbtbase.func_152446_a(datainput, i, nbtreadlimiter);
                return nbtbase;
            } catch (IOException ioexception) {
                CrashReport crashreport = CrashReport.func_85055_a(ioexception, "Loading NBT data");
                CrashReportCategory crashreportsystemdetails = crashreport.func_85058_a("NBT Tag");

                crashreportsystemdetails.func_71507_a("Tag type", (Object) Byte.valueOf(b0));
                throw new ReportedException(crashreport);
            }
        }
    }
}
