public class ODS<U>
{
    private U[] data;

    public ODS()
    {
        data=(U[]) new Object[0];
    }

    public ODS(U[]data)
    {
        this.data=data;
    }
    /*public ODS(Object[]data)
    {
        
        this.data= (U[])new Object[data.length];

        for(int ll=0; ll<data.length; ll++)
        {
            this.data[ll]=(U)data[ll];
        }
    }*/

    public Object[] toArray()
    {
        return data;
    }
    
    public boolean  Insert(U dataE)
    {
        if(Contains(dataE))
            return false;
        else{
            U[] arr=(U[])  new Object[data.length];
            //process
            for(int v=0; v<data.length; v++)
            {
                arr[v]=data[v];
            }
            data= (U[]) new Object[data.length+1]; 
            data[arr.length]=dataE;
            for(int f=0; f<arr.length; f++)
            {
                data[f]=arr[f];
            }

            return true;
        }
    }

    public boolean Contains(U dataE)
    {
        if(Search(dataE) != null)
            return true;
        return false;
    }

    public U Search(U dataE)
    {
        int c=0;
        for(U gg: data)
        {
            if(gg.equals(dataE))
            {
                return gg;
            }
            c++;
        }
        return null;
    }

    public int Search(U dataE, boolean v)
    {
        int c=0;
        for(U gg: data)
        {
            if(gg.equals(dataE))
            {
                return c;
            }
            c++;
        }
        return Integer.MIN_VALUE;
    }

    public boolean Remove(U dataE)
    {
        if(Contains(dataE))
        {
            U[] arr = (U[])  new Object[data.length];
            for(int c=0; c<data.length; c++)
            {
                arr[c]=data[c];
            }
            int skip=Search(dataE, false);
            data = (U[]) new Object[data.length-1];
            int gena=0;
            int nu=-1;
            for(int v = 0; v < data.length; v++)
            {
                nu++;
                if(v == skip)
                {
                    gena++;
                }
    
                data[v]=arr[gena++];

            
            }
            skip=nu;
            return true;
        }
        return false;
    }

    @Override
   public boolean equals(Object ods)
   {
        if(ods==null)
            return false;
        if(this != ods)
        {
            int index=0;
            if(this.getClass() != ods.getClass() )
            {
                return false;
            }
            else if( this.data.length  !=  ( (ODS <U> ) ods) .data.length )
            {
                return false;
            }

            for(int ff=0; ff<data.length; ff++)
            {
                index=ff;
                if( ! this.data[ff].equals( ((ODS<U>)ods).data[ff]  ) )
                {
                    ff=index;
                    return false;
                }
            }
            return true;
        }    
        return true;

    }


}