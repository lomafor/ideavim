package com.maddyhome.idea.vim.ex.handler;

/*
* IdeaVim - A Vim emulator plugin for IntelliJ Idea
* Copyright (C) 2003 Rick Maddy
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.ex.CommandHandler;
import com.maddyhome.idea.vim.ex.CommandName;
import com.maddyhome.idea.vim.ex.ExCommand;
import com.maddyhome.idea.vim.ex.ExException;
import com.maddyhome.idea.vim.ex.LineRange;

/**
 *
 */
public class DumpLineHandler extends CommandHandler
{
    public DumpLineHandler()
    {
        super(new CommandName[] {
            new CommandName("dump", "line")
        }, RANGE_OPTIONAL);
    }

    public boolean execute(Editor editor, DataContext context, ExCommand cmd) throws ExException
    {
        LineRange range = cmd.getLineRange(editor, context, false);

        char[] chars = editor.getDocument().getChars();
        for (int l = range.getStartLine(); l <= range.getEndLine(); l++)
        {
            int start = editor.getDocument().getLineStartOffset(l);
            int end = editor.getDocument().getLineEndOffset(l);
            logger.debug("Line " + l + ", start offset=" + start + ", end offset=" + end);

            for (int i = start; i <= end; i++)
            {
                logger.debug("Offset " + i + ", char=" + chars[i] + ", lp=" + editor.offsetToLogicalPosition(i) +
                    ", vp=" + editor.offsetToVisualPosition(i));
            }
        }

        return true;
    }

    private static Logger logger = Logger.getInstance(DumpLineHandler.class.getName());
}