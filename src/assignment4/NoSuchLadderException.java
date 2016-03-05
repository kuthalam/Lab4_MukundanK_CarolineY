/* Name: Mukundan Kuthalam, Caroline Yao
 * EID: mk33274, chy253
 * EE 422C - Assignment 4
 */

package assignment4;

public class NoSuchLadderException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public NoSuchLadderException(String message)
    {
        super(message);
    }

    public NoSuchLadderException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
